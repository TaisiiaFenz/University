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
    actions: {
        async addClient({dispatch, commit}, {firstName, middleName, lastName, passport, birthday,
            clientEmail, clientPassword}) {
            try {
                console.log(firstName, lastName);
                let response = await fetch('https://api.coindesk.com/v1/bpi/currentprice.json');
                console.log(response);
                //TODO: отправить запрос с новым клиентом на бек
                // let res = await this.axios
                //     .get('https://api.coindesk.com/v1/bpi/currentprice.json')
                //     .then(response => (console.log(response)));
                // console.log("res ", res);
                // axios.get()
            } catch (e) {
                throw e
            }
        },
        async fetchDiscountOptions({dispatch, commit}) {
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
            } catch (e) {}
        },
        async fetchReservedTours({dispatch, commit}) {
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
            } catch (e) {}
        },
        async approveTour({dispatch, commit}, formData) {
            try {
                let response = await fetch('https://api.coindesk.com/v1/bpi/currentprice.json');
                console.log(response);
                //TODO отправить запрос на бек со скидоном и айди тура
            } catch (e) {}
        },
        async notApproveTour({dispatch, commit}, formData) {
            try {
                let response = await fetch('https://api.coindesk.com/v1/bpi/currentprice.json');
                console.log(response);
                //TODO отправить запрос на бек с айди тура
            } catch (e) {}
        }
    },
    getters: {
        discountOptions: s => s.discountOptions,
        reservedTours: s => s.reservedTours
    }
}
