const state = {
  token: localStorage.getItem("token"),
  userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
  loginDialogVisible: false,
};

const getters = {
  getUser: (state) => {
    return state.userInfo;
  },
  isLogin: (state) => {
    return state.token !== null;
  },
};

const mutations = {
  setToken: (state, token) => {
    state.token = token;
    localStorage.setItem("token", token);
  },
  setUserInfo: (state, userInfo) => {
    state.userInfo = userInfo;
    sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
  },
  removeUserInfo: (state) => {
    state.token = null;
    state.userInfo = null;
    sessionStorage.removeItem("userInfo");
    localStorage.removeItem("token");
  },
  showLoginDialog: (state) => {
    state.loginDialogVisible = true;
  },
  hideLoginDialog: (state) => {
    state.loginDialogVisible = false;
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
