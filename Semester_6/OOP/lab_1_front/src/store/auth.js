//import axios from 'axios';

export default {
    actions: {
        async login({dispatch, commit}, formData) {
            //try {
            console.log(dispatch + commit);
                console.log(formData);
                let resp = await fetch('http://192.168.50.76:8083/login', {
                    method: 'POST',
                    //mode: 'no-cors',
                    body: JSON.stringify(formData)
                });
                console.log(resp);

                // let response = await resp.json();
                //  console.log(response);
                //TODO: отправить логин с паролем и вернуть роль
                // let res = await axios.post('http://localhost:8083/login', {
                //     method: 'POST',
                //     body: JSON.stringify(formData),
                //     mode: 'no-cors',
                //     headers: {
                //         'Access-Control-Allow-Origin': '*',
                //         Accept: 'application/json',
                //         'Content-Type': 'application/json',
                //     },
                //     withCredentials: true,
                //     credentials: 'same-origin',
                //     crossdomain: true
                // });
                // console.log(res);

            // const testURL = 'http://localhost:8083/login';
            // const myInit = {
            //     method: 'POST',
            //     mode: 'no-cors',
            //     body: JSON.stringify(formData)
            // };
            //
            // const myRequest = new Request(testURL, myInit);
            //
            // fetch(myRequest).then(function(response) {
            //     return response;
            // }).then(function(response) {
            //     console.log(response);
            // }).catch(function(e){
            //     console.log(e);
            // });
                //     .then(response => (console.log(response)));
                // console.log("res ", res);
                // axios.get()
            //} catch (e) {}
        },
        getUid() {
            //TODO: get id of user?
            // const user = firebase.auth().currentUser;
            // return user ? user.uid : null;
            return 1;
        }
    }

}
