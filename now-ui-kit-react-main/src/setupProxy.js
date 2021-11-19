const { createProxyMiddleware } = require('http-proxy-middleware');

// src/setupProxy.js
module.exports = function(app) {
    app.use(
        '/login',
        createProxyMiddleware( {
            target:"http://localhost:28080/demo", // 비즈니스 서버 URL 설정
            changeOrigin: true
        })
    );
};
