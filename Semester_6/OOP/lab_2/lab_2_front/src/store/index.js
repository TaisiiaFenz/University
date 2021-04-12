import Vue from "vue";
import Vuex from "vuex";
import auth from "./auth";
import admin from "./admin";
import client from "./client";
import tour from "./tour";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    auth,
    admin,
    client,
    tour
  }
});
