import axios from "axios";

export default {
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
        }
    }

}
