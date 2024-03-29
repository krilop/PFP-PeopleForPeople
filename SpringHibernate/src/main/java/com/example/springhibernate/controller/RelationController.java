package com.example.springhibernate.controller;

import com.example.springhibernate.service.impl.RelationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/PFP")
public class RelationController {

    private final RelationService relationService;

    @PostMapping("/relations/new")
    public ResponseEntity<?> createNewRelationType(@RequestParam("title") String title)
    {
        return relationService.createNewRelationType(title);
    }

    @PostMapping("/relations/{userId}/{anotherUserId}")
    public ResponseEntity<?> createNewRelationBetweenUsers(@PathVariable("userId") Long userId, @PathVariable("anotherUserId") Long anotherId, @RequestParam("relationId") Long relationId)
    {
        return relationService.createNewRelation(userId, anotherId, relationId);
    }

    @GetMapping("/profile/{id}/relations")
    public ResponseEntity<?> findAllRelationsForUser(@PathVariable("id")Long id)
    {
        return relationService.findAllRelationsForUser(id);
    }
}
