import jwt_decode from "jwt-decode"

export default {
    state: {
        reservedToursByClient: []
    },
    mutations: {
        setReservedToursByClient(state, reservedToursByClient) {
            state.reservedToursByClient = reservedToursByClient;
        }
    },
    actions: {
        async reserveTourByClient({dispatch, commit}, formData) {
            try {
                console.log(dispatch, commit);
                let token = this.userToken;
                console.log(token);
                let decoded = jwt_decode(token);
                let resultData = {
                    role: decoded.authorities,
                    userId: decoded.user_id
                };
                console.log(resultData);
                formData["userId"] = `${resultData.userId}`;
                console.log(formData);
                let response = await fetch('http://localhost:8083/reserve-tour', {
                    method: 'POST',
                    headers: {
                        Authorization: `Bearer ${token}`
                    },
                    body: JSON.stringify(formData)
                });
                console.log(response);
            } catch (e) {
                alert("Sorry, sth go wrong:(");
            }
        },
        async fetchReservedToursByClient({dispatch, commit}) {
            try {
                console.log(dispatch, commit);
                let token = this.userToken;
                console.log(token);
                let decoded = jwt_decode(token);
                let resultData = {
                    role: decoded.authorities,
                    userId: decoded.user_id
                };
                console.log(resultData);
                let response = await fetch(`http://localhost:8083/get-my-reservations?id=${resultData.userId}`, {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                let respJson = await response.json();
                console.log(respJson);
                commit('setReservedToursByClient', respJson.reservedTours);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
                throw e;
            }
        },
    },
    getters: {
        reservedToursByClient: s => s.reservedToursByClient
    }
}
