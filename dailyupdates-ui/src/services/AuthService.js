
const signin = async (loginPayload) => {
    const response = await fetch('http://192.168.2.103:8080/api/auth/login', {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(loginPayload),
        credentials: "include"
    })

    const body = await response.json();

    if (!response.ok) {
        throw { httpStatus: response.status };
    }

    return body;
}

const signup = async (signupPayload) => {
    const response = await fetch('http://192.168.2.103:8080/api/auth/signup', {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(signupPayload),
        credentials: "include"
    })

    const body = await response.json();

    if (!response.ok) {
        throw { httpStatus: response.status };
    }

    return body;
}

export default {
    signin,
    signup
}