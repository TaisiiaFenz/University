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
            try {
                //TODO: как нить получить общий список туров
                // const uid = await dispatch('getUid');
                // //как нить получить общий список туров
                // const info = await (firebase.database().ref(`/users/${uid}/info`).once('value')).val();
                const info = {
                    "tours": [
                        {
                            "id": "0",
                            "name": "Amazing tour",
                            "tourType": "RELAXATION",
                            "transportType": "BUS",
                            "country": "Spain",
                            "price": "$500",
                            "isLastMinuteTour": "true"
                        }
                ]}
                commit('setTours', info.tours);
            } catch (e) {}
        },
        async updateTour({dispatch, commit}, checkBoxId) {
            try {
                let response = await fetch('https://api.coindesk.com/v1/bpi/currentprice.json');
                console.log(response);
                //TODO: отправить обновления горящий тур или нет
                // let res = await this.axios
                //     .get('https://api.coindesk.com/v1/bpi/currentprice.json')
                //     .then(response => (console.log(response)));
                // console.log("res ", res);
                // axios.get()
            } catch (e) {
                throw e
            }
        }
    },
    getters: {
        tours: s => s.tours
    }
}
