import axios from "axios";

export default {
    actions: {
        async login({dispatch, commit}, {email, password}) {
            try {
                console.log(email, password);
                let response = await fetch('https://api.coindesk.com/v1/bpi/currentprice.json');
                console.log(response);
                //TODO: отправить логин с паролем и вернуть роль
                // let res = await this.axios
                //     .get('https://api.coindesk.com/v1/bpi/currentprice.json')
                //     .then(response => (console.log(response)));
                // console.log("res ", res);
                // axios.get()
            } catch (e) {
                throw e
            }
        },
        getUid() {
            //TODO: get id of user?
            // const user = firebase.auth().currentUser;
            // return user ? user.uid : null;
            return 1;
        }
    }

}
