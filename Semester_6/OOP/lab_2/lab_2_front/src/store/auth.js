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
                let resp = await fetch('http://localhost:8081/api/auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    body: JSON.stringify(formData)
                });
                respJson = await resp.json();
            } catch (e) {
                alert("Smth go wrong :(");
            }
            console.log(respJson);
                let token = respJson.accessToken;
                commit('setUserToken', token);
                console.log(token);
                let decoded = jwt_decode(token);
                commit('setUserName', decoded.authorities);
                console.log(decoded);
                let authorities;
                if (decoded.sub === "100") {
                    authorities = "AGENT";
                } else {
                    authorities = "CLIENT";
                }
                let resultData = {
                    role: authorities,
                    userId: decoded.sub
                };
                return resultData;
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
