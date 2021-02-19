import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "login",
    component: () => import("../views/Login.vue")
   },
  {
    path: "/add-client",
    name: "AddClient",
    meta: {
      layout: "admin"
    },
    component: () =>
      import("../views/AdminAddClient.vue")
  },
  {
    path: "/add-tour",
    name: "AddTour",
    meta: {
      layout: "admin"
    },
    component: () =>
        import("../views/AdminAddTour.vue")
  },
  {
    path: "/reserved-tour",
    name: "AdminReservedTour",
    meta: {
      layout: "admin"
    },
    component: () =>
        import("../views/AdminReservedTours.vue")
  },
  {
    path: "/accepted-tour",
    name: "AdminAcceptedTour",
    meta: {
      layout: "admin"
    },
    component: () =>
        import("../views/AdminAcceptedTours.vue")
  },
  {
    path: "/admin-tours",
    name: "AdminTours",
    meta: {
      layout: "admin"
    },
    component: () =>
        import("../views/AdminTours.vue")
  },
  {
    path: "/client-tours",
    name: "ClientTours",
    meta: {
      layout: "client"
    },
    component: () =>
        import("../views/ClientTours.vue")
  },
  {
    path: "/client-reserved-tours",
    name: "ClientReservedTours",
    meta: {
      layout: "client"
    },
    component: () =>
        import("../views/ClientReservedTours.vue")
  },
  {
    path: "/client-accepted-tours",
    name: "ClientAcceptedTours",
    meta: {
      layout: "client"
    },
    component: () =>
        import("../views/ClientAcceptedTours.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;