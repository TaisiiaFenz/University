import axios from "axios";

export default {
    actions: {
        async login({dispatch, commit}, {email, password}) {
            try {
                console.log(email, password);
                let response = await fetch('https://api.coindesk.com/v1/bpi/currentprice.json');
                console.log(response);
                // let res = await this.axios
                //     .get('https://api.coindesk.com/v1/bpi/currentprice.json')
                //     .then(response => (console.log(response)));
                // console.log("res ", res);
                // axios.get()
            } catch (e) {
                throw e
            }
        }
    }

}
