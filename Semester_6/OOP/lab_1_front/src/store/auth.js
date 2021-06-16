import jwt_decode from "jwt-decode";

export default {
    state: {
        userToken: String,
        userName: String
    },
    mutations: {
        setUserToken(state, userToken) {
            this.userToken = userToken;
        },
        setUserName(state, userName) {
            this.userName = userName;
        }
    },
    computed: {
        userName() {
            return this.$store.getters.userName;
        }
    },
    actions: {
        async login({dispatch, commit}, formData) {
            let respJson;
            console.log(dispatch, commit);
            try {
                let resp = await fetch('http://localhost:8083/login', {
                    method: 'POST',
                    body: JSON.stringify(formData)
                });
                respJson = await resp.json();
            } catch (e) {
                alert("Smth go wrong :(");
            }
            if (!respJson.isSuccess) {
                alert("Sorry, there is no such client :(");
            } else {
                let token = respJson.token;
                commit('setUserToken', token);
                let decoded = jwt_decode(token);
                commit('setUserName', decoded.authorities);
                console.log(decoded.authorities);
                let resultData = {
                    role: decoded.authorities,
                    userId: decoded.user_id
                };
                return resultData;
            }
        },
        getUserName({dispatch, commit}) {
            console.log(dispatch, commit);
            let result = "test";
            // new Promise((resolve) => {
            //     resolve("testing");
            // }).then((res) => result = res);
            return result;
        }
    },
    getters: {
        userToken: s => s.userToken,
        userName: s => s.userName
    }
}
