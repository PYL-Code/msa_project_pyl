function createFakeJwt() {
    const header = {
        alg: "HS256",
        typ: "JWT"
    };

    const payload = {
        id: 1, //이 값을 바꾸어서 테스트 시 유저 전환
        sub: "testUser",
        iat: Math.floor(Date.now() / 1000),
        exp: Math.floor(Date.now() / 1000) + 60 * 60 // 1시간 후 만료
    };

    const base64UrlEncode = (obj) =>
        btoa(JSON.stringify(obj))
            .replace(/=/g, '')
            .replace(/\+/g, '-')
            .replace(/\//g, '_');

    const unsignedToken = `${base64UrlEncode(header)}.${base64UrlEncode(payload)}`;
    const fakeSignature = "fake-signature"; // 실제 서명은 하지 않음

    return `${unsignedToken}.${fakeSignature}`;
}

export default createFakeJwt;