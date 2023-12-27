"use client"

import AuthService from "@/services/AuthService";

const { createContext, useState, useContext, useEffect } = require("react");

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [currentUser, setCurrentUser] = useState();

    useEffect(() => {
        const storedUser = localStorage.getItem("currentUser")

        if (storedUser) {
            setCurrentUser(JSON.parse(storedUser))
        }
    }, []);

    const loginUser = async (formData) => {
        const res = await AuthService.signin(formData);
        localStorage.setItem("currentUser", JSON.stringify(res.data));
        setCurrentUser(res.data);
        window.location.href = "/";
    }

    const logoutUser = async () => {
        await AuthService.signout();
        localStorage.clear();
        window.location.href = "/"
    }

    // const checkAuthenticatedUser = async () => {
    //     const response = await fetch('http://192.168.2.103:8080/api/auth/whoami', {
    //         method: "GET",
    //         credentials: "include",
    //         cache: "no-cache"
    //     })
    //     console.log(response.status)
    //     if (response.status == 200) {
    //         const res = await response.json();
    //         setCurrentUser(res.data);
    //     }
    // }

    return <AuthContext.Provider value={{ currentUser, loginUser, logoutUser }}>{children}</AuthContext.Provider>;
}

export const useAuth = () => {
    return useContext(AuthContext);
}