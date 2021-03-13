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
            let token = this.userToken;
            try {
                let resp = await fetch('http://localhost:8083/get-all-discounts', {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                let respJson = await resp.json();
                console.log(respJson);
                let discounts = [];
                respJson.forEach((item) => {
                    discounts.push(item.discountType);
                });
                commit('setDiscountOptions', discounts);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
            }
        },

        async fetchReservedTours({dispatch, commit}) {
            console.log(dispatch, commit);
            let token = this.userToken;
            let resp = await fetch('http://localhost:8083/get-all-reservations', {
                method: 'GET',
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            let respJson = await resp.json();
            console.log(respJson);
            try {
                commit('setReservedTours', respJson.reservedTours);
            } catch (e) {
                alert("Sorry, smth go wrong :(");
            }
        },

        async approveTour({dispatch, commit}, formData) {
            console.log(dispatch, commit, formData);
            let token = this.userToken;
            try {
                let response = await fetch('http://localhost:8083/accept-reservation', {
                    method: 'POST',
                    headers: {
                        Authorization: `Bearer ${token}`
                    },
                    body: JSON.stringify(formData)
                });
                console.log(response);
            } catch (e) {alert("Sorry, smth go wrong :(");}
        },
        async notApproveTour({dispatch, commit}, formData) {
            console.log(dispatch, commit, formData);
            let token = this.userToken;
            try {
                let response = await fetch('http://localhost:8083/cancel-reservation', {
                    method: 'POST',
                    headers: {
                        Authorization: `Bearer ${token}`
                    },
                    body: JSON.stringify(formData)
                });
                console.log(response);
            } catch (e) {alert("Sorry, smth go wrong :(");}
        }
    },
    getters: {
        discountOptions: s => s.discountOptions,
        reservedTours: s => s.reservedTours
    }
}
