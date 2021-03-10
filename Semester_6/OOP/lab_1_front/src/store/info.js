export default {
    state: {
        tours: []
    },
    mutations: {
        setTours(state, tours) {
            state.tours = tours;
        },
        clearTours(state) {
            state.tours = {};
        }
    },
    actions: {
        async fetchTours({dispatch, commit}) {
            console.log(dispatch, commit);
            let token = this.userToken;
            try {
                let resp = await fetch('http://localhost:8083/get-tours', {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                let respJson = await resp.json();
                console.log(respJson);
                commit('setTours', respJson.tours);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
                throw e;
            }
        },
        async updateTour({dispatch, commit}, checkBoxId) {
            try {
                console.log(dispatch, commit, checkBoxId);
                let token = this.userToken;
                let response = await fetch(`http://localhost:8083/update-hot-tour?id=${checkBoxId}`, {
                    method: 'POST',
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                console.log(response);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
                throw e
            }
        },
    },
    getters: {
        tours: s => s.tours
    }
}
