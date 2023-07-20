import { darkTheme } from "naive-ui";

const state = {
  isDarkTheme: false,
};

const getters = {
  getTheme: (state) => {
    return state.isDarkTheme ? darkTheme : null;
  },
};

const mutations = {
  toggleTheme: (state) => {
    state.isDarkTheme = !state.isDarkTheme;
  },
};

const actions = {};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
