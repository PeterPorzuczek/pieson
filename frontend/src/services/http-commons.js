import axios from 'axios'

export const http = axios.create({
  baseURL: `http://pieson.herokuapp.com`,
  validateStatus: function (status) {
    return status >= 200 && status < 300
  }
})
