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
                let response = await fetch('https://api.coindesk.com/v1/bpi/currentprice.json');
                console.log(response);
                //TODO отправить запрос на бек со скидоном и айди тура
            } catch (e) {
            }
        },
        async fetchReservedToursByClient({dispatch, commit}, clientId) {
            try {
                //TODO: как нить получить список зарезервированных туров по конкретному пользователю
                // const uid = await dispatch('getUid');
                // //как нить получить общий список туров
                // const info = await (firebase.database().ref(`/users/${uid}/info`).once('value')).val();
                const info = {
                    "tours": [
                        {
                            "id": "0",
                            "name": "Reserved tour",
                            "tourType": "RELAXATION",
                            "transportType": "BUS",
                            "country": "Spain",
                            "price": "$500",
                            "isLastMinuteTour": "true"
                        }
                    ]}
                commit('setReservedToursByClient', info.tours);
            } catch (e) {}
        },
    },
    getters: {
        reservedToursByClient: s => s.reservedToursByClient
    }
}
