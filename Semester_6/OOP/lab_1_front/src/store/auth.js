import jwt_decode from "jwt-decode";

export default {
    actions: {
        async login({dispatch, commit}, formData) {
            let respJson;
            console.log(dispatch, commit);
            try {
                let resp = await fetch('http://localhost:8083/login', {
                    method: 'POST',
                    //mode: 'no-cors',
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
                let decoded = jwt_decode(token);
                let resultData = {
                    role: decoded.authorities,
                    userId: decoded.user_id
                };
                return resultData;
            }
        }
    }
}
