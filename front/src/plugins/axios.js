"use strict";

import Vue from 'vue';
import axios from "axios";
import router from "../router";

let config = {
    baseURL: 'http://localhost:8081'
};

const _axios = axios.create(config);

_axios.interceptors.request.use(
    function(config) {
        const jwt = localStorage.getItem('jwt');
        config.headers.Authorization = `Bearer ${jwt}`
        // Do something before request is sent
        return config;
    },
    function(error) {
        // Do something with request error
        return Promise.reject(error);
    }
);

// Add a response interceptor
_axios.interceptors.response.use(
    function(response) {
        // Do something with response data
        return response;
    },
    function(error) {
        // Do something with response error

        if (error && error.response && error.response.status === 401) {
            // window.location = '/login'
            router.push({name: 'Login'});
        }

        return Promise.reject(error);
    }
);

// eslint-disable-next-line no-unused-vars
Plugin.install = function(Vue, options) {
    Vue.axios = _axios;
    window.axios = _axios;
    Object.defineProperties(Vue.prototype, {
        axios: {
            get() {
                return _axios;
            }
        },
        $axios: {
            get() {
                return _axios;
            }
        },
    });
};

Vue.use(Plugin)
export default Plugin;
