import axios from "axios";

const apiUrl = "http://localhost:8080/api/v1/PFP";

const InterestsService = {
    getInterests(){
        const token = localStorage.getItem('token');
        return axios.get(`${apiUrl}/interests/all`, {headers:{
                'Authorization': `Bearer ${token}`,
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }});
    },
    getInterestsForUser() {
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('id');
        return axios.get(`${apiUrl}/interests/${userId}/new`,{headers:{
                'Authorization': `Bearer ${token}`,
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }});
    },
    getInterestsOfUser (id)  {
        const token = localStorage.getItem('token');
        return axios.get(`${apiUrl}/interests/${id}`,{headers:{
                'Authorization': `Bearer ${token}`,
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }});
    },
    deleteInterestOfUser(id) {
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('id');
        return axios.delete(`${apiUrl}/interests/${userId}/${id}`, {headers:{
                'Authorization': `Bearer ${token}`,
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }});
    },
    saveInterest (interest)  {
        const token = localStorage.getItem('token');
        return axios.post(`${apiUrl}/interests/addNew`, interest, {headers:{
                'Authorization': `Bearer ${token}`,
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }});
    },
    addInterestForUser(id) {
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('id');
        return axios.post(`${apiUrl}/interests/${userId}/${id}`, '', {headers:{
                'Authorization': `Bearer ${token}`,
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }});
    }
};

export default InterestsService;
