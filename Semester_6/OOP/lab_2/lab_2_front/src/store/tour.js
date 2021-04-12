export default {
    state: {
        tours: []
    },
    mutations: {
        setTours(state, tours) {
            state.tours = tours;
        }
    },
    actions: {
        async fetchTours({dispatch, commit}) {
            console.log(dispatch, commit);
            let token = this.userToken;
            try {
                let resp = await fetch('http://localhost:8081/tours/all', {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'Access-Control-Allow-Origin': '*'
                    }
                });
                let respJson = await resp.json();
                commit('setTours', respJson.tours);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
                throw e;
            }
        },

        async updateTour({dispatch, commit}, checkBoxId) {
            try {
                console.log(dispatch, commit);
                let token = this.userToken;
                let response = await fetch(`http://localhost:8081/agent/update-hot-tour/${checkBoxId}`, {
                    method: 'PUT',
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'Access-Control-Allow-Origin': '*'
                    }
                });
                console.log(response);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
                throw e
            }
        }
    },
    getters: {
        tours: s => s.tours
    }
}
