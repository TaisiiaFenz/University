export default {
    state: {
        discountOptions: [],
        reservedTours: []
    },
    mutations: {
        setDiscountOptions(state, discountOptions) {
            state.discountOptions = discountOptions;
        },
        setReservedTours(state, reservedTours) {
            state.reservedTours = reservedTours;
        }
    },
    computed: {
        userToken() {
            return this.$store.getters.userToken;
        }
    },
    actions: {
        async addClient({dispatch, commit}, formData) {
            console.log(dispatch, commit);
            let token = this.userToken;
            console.log(token);
             try {
                let response = await fetch('http://localhost:8083/add-client', {
                    method: 'POST',
                    headers: {
                        Authorization: `Bearer ${token}`
                    },
                    body: JSON.stringify(formData)
                });
                console.log(response);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
                throw e
            }
        },
        async fetchDiscountOptions({dispatch, commit}) {
            console.log(dispatch, commit);
            try {
                //TODO: как нить получить список дисконтов
                // const uid = await dispatch('getUid');
                // //как нить получить общий список туров
                // const info = await (firebase.database().ref(`/users/${uid}/info`).once('value')).val();
                const info = {
                    "discount": [
                        "OftenUser", "Test"
                    ]
                };
                commit('setDiscountOptions', info.discount);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
            }
        },
        async fetchReservedTours({dispatch, commit}) {
            console.log(dispatch, commit);
            try {
                //TODO: как нить получить список зарезервированных туров
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
                commit('setReservedTours', info.tours);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
            }
        },
        async approveTour({dispatch, commit}, formData) {
            console.log(dispatch, commit, formData);
            try {
                let response = await fetch('https://api.coindesk.com/v1/bpi/currentprice.json');
                console.log(response);
                //TODO отправить запрос на бек со скидоном и айди тура
            } catch (e) {alert("Sorry, smth go wrong :(");}
        },
        async notApproveTour({dispatch, commit}, formData) {
            console.log(dispatch, commit, formData);
            try {
                let response = await fetch('https://api.coindesk.com/v1/bpi/currentprice.json');
                console.log(response);
                //TODO отправить запрос на бек с айди тура
            } catch (e) {alert("Sorry, smth go wrong :(");}
        }
    },
    getters: {
        discountOptions: s => s.discountOptions,
        reservedTours: s => s.reservedTours
    }
}
