export const CLIENT_ID = 'svappspaid'
export const CLIENT_SECRET = 'wT4kOb2N1olO0'
export const CLIENT_TOKEN_NAME = 'access_token'
export const CLIENT_TOKEN_TYPE_NAME = 'token_type'
export const CLIENT_TOKEN_TYPE = 'bearer'
export const AUTHORIZATION_HEADER = 'Authorization'
export const TOKEN_GET = '/oauth/token'
export const AUTH_OPTIONS = function (username, password) {
  const dataParams = new URLSearchParams()
  dataParams.append('grant_type', 'password')
  dataParams.append('username', encodeURIComponent(username))
  dataParams.append('password', encodeURIComponent(password))
  const authOptions = {
    method: 'POST',
    url: TOKEN_GET,
    data: dataParams,
    headers: {
      'Authorization': 'Basic ' + btoa(CLIENT_ID + ':' + CLIENT_SECRET),
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    json: true
  }
  return authOptions
}
export const TOKEN_DECODE = function (accessData) {
  const tokenSplited = accessData[CLIENT_TOKEN_NAME].split('.')
  const decodedToken = JSON.parse(atob(tokenSplited[1]))
  return decodedToken
}
export const VUE_ACL_SESSION = 'vue-acl-permissions'
