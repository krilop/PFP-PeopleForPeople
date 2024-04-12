package com.example.springhibernate.service.impl;

import com.example.springhibernate.model.Pair;
import com.example.springhibernate.model.RelationType;
import com.example.springhibernate.model.UserData;
import com.example.springhibernate.repository.PairRepository;
import com.example.springhibernate.repository.RelationTypeRepository;
import com.example.springhibernate.repository.UserDataRepository;
import com.example.springhibernate.service.RelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RelationService implements RelService {

    UserDataRepository userDataRepository;
    RelationTypeRepository relationTypeRepository;

    PairRepository pairRepository;

    @Autowired
    public RelationService(UserDataRepository userDataRepository, RelationTypeRepository relationTypeRepository, PairRepository repository)
    {
        this.relationTypeRepository=relationTypeRepository;
        this.pairRepository =repository;
        this.userDataRepository=userDataRepository;
    }

    public ResponseEntity<?> createNewRelationType(String title)
    {
        RelationType New = new RelationType();
        New.setRelationTitle(title);
        return new ResponseEntity<>(relationTypeRepository.save(New), HttpStatus.CREATED);
    }

    public ResponseEntity<?> findAllRelations()
    {
        return new ResponseEntity<>(relationTypeRepository.findAll(), HttpStatus.OK);
    }

    /** Данный метод ДОБАВЛЯЕТ новые отношения для пары, НО ТОЛЬКО С ОДНОЙ СТОРОНЫ **/
    @Transactional
    public ResponseEntity<?> createNewRelation(Long userId, Long anotherId, Long relationId)
    {
        Optional<UserData> user = userDataRepository.findUserDataById(userId);
        Optional<UserData> another = userDataRepository.findUserDataById(anotherId);
        boolean rel = relationTypeRepository.existsById(relationId);
        if(user.isEmpty()||another.isEmpty()||!rel)//проверяем наличие всех данных
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Pair newPair;
        int cnt = pairRepository.countByUserId_IdAndAnotherId_Id(userId, anotherId);
        cnt+=  pairRepository.countByUserId_IdAndAnotherId_Id(anotherId, userId); //смотрим, сколько у нас таких пар значений
        Optional<RelationType> relationType = relationTypeRepository.findById(relationId);
        if(cnt==0) {
            //пар 0, значит, просто создаем новую пару и добавляем ей запрошенные взаимоотношения
            newPair = new Pair();
            newPair.setDirection(true);
            newPair.setUserId(user.get());
            newPair.setAnotherId(another.get());
            newPair.setRelationTypes(new ArrayList<>());
            newPair.addRelationTypes(relationType.get());
        }
        else if(cnt==1){
            //пара одна. Тогда нам нужно определить, мы в базе храним данные от этого пользователя о взаимоотношениях или от другого. (direcrion была добавлена, чтобы на уровне базы сделать так, чтобы записей для пары было не больше двух)
            newPair = pairRepository.findByUserId_IdAndAnotherId_Id(userId,anotherId);
            //после взятия пары, смотрим на порядок в столбцах. Если наша проверка вернула true, значит, данные принадлежат этому пользователю. Тогда просто навешиваем новые отношения
            if(newPair!=null)
            {
                newPair.addRelationTypes(relationType.get());
            }
            else
            {//иначе, меняем направление на копии, убираем уже существующий список, т.к. он не наш. И добавляем новые взаимоотношения
                newPair = new Pair();
                newPair.setDirection(false);
                newPair.setUserId(user.get());
                newPair.setAnotherId(another.get());
                newPair.addRelationTypes(relationType.get());
            }
        }
        else{//случай, когда уже есть данные и с одной стороны, и с другой
            //Данный метод возвращает пару с конкретным порядком параметров. Пару, которая принадлежит нашему пользователю
            newPair = pairRepository.findByUserId_IdAndAnotherId_Id(userId,anotherId);
            //навешиваем на неё новые отношения
            newPair.addRelationTypes(relationType.get());
        }
        //сохраняем полученную пару
        return new ResponseEntity<>(pairRepository.save(newPair),HttpStatus.CREATED);
    }

    public ResponseEntity<?> findAllRelationsForUser(Long id) {
        Optional<UserData> user = userDataRepository.findUserDataById(id);
        List<Pair> pairs = pairRepository.findPairsByUserId(user.get());
        if (pairs == null || pairs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        ArrayNode usersArray = objectMapper.createArrayNode();

        for (Pair pair : pairs) {
            ObjectNode userNode = objectMapper.createObjectNode();
            userNode.put("name", pair.getAnotherId().getName()); // another_id в pairs
            ArrayNode relationTitlesArray = objectMapper.createArrayNode();

            for (RelationType relationType : pair.getRelationTypes()) {
                relationTitlesArray.add(relationType.getRelationTitle());
            }

            userNode.set("relation_titles", relationTitlesArray);
            usersArray.add(userNode);
        }

        rootNode.set("users", usersArray);

        return ResponseEntity.ok(rootNode);
    }
}
