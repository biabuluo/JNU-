import { createStore } from "vuex";
import theme from "./modules/theme";
import login from "./modules/login";

export default createStore({
  modules: {
    theme,
    login,
  },
});
