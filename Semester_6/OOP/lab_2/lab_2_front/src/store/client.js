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
                let decoded = jwt_decode(token);
                console.log(decoded);
                let resultData = {
                    userId: decoded.sub
                };
                formData["userId"] = `${resultData.userId}`;
                console.log(formData);
                let response = await fetch('http://localhost:8081/client/reserve-tour', {
                    method: 'POST',
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'Content-Type': 'application/json; charset=utf-8',
                        'Access-Control-Allow-Origin': '*'
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
                let decoded = jwt_decode(token);
                let resultData = {
                    role: decoded.authorities,
                    userId: decoded.user_id
                };
                console.log(resultData);
                let response = await fetch(`http://localhost:8081/client/my-reservations`, {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'Content-Type': 'application/json; charset=utf-8',
                        'Access-Control-Allow-Origin': '*'
                    }
                });
                let respJson = await response.json();
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
