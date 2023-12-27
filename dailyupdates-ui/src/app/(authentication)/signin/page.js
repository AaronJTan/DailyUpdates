"use client"

import ErrorText from "@/components/ErrorText";
import { useAuth } from "@/hooks/authContext";
import { useFormFields } from "@/hooks/useFormFields";
import AuthService from "@/services/AuthService";
import { handleInputErrorClass } from "@/utils/cssUtils";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function SignInPage() {
    const {loginUser} = useAuth();

    const router = useRouter();
    const [formData, handleChange] = useFormFields({
        username: "",
        password: ""
    })

    const [errors, setErrors] = useState({});

    const onSubmit = async (e) => {
        e.preventDefault();

        const validationErrors = validateForm(formData);
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        setErrors({});

        try {
            await loginUser(formData);
        } catch (error) {
            if (error.httpStatus == 401) {
                setErrors({ invalidCredentials: true })
            }
        }
    }

    const validateForm = (formData) => {
        const errors = {};

        if (!formData.username) {
            errors.username = 'Email or username is required';
        }
        if (!formData.password) {
            errors.password = 'Password is required';
        }
        return errors;
    }

    return (
        <div className="mx-auto max-w-[600px] my-4 bg-white">

            <form className="flex flex-col sm:px-20 p-4 gap-2"
                onSubmit={onSubmit}
            >
                <div>
                    <label htmlFor="username">Email or username</label>
                    <input type="text" id="username" name="username"
                        value={formData.usernameOrEmail} onChange={handleChange}
                        className={handleInputErrorClass(errors.password)}
                    />
                    {errors.username && <ErrorText>{errors.username}</ErrorText>}
                </div>

                <div>
                    <label htmlFor="password">Password</label>
                    <input type="password" id="password" name="password"
                        value={formData.password} onChange={handleChange}
                        className={handleInputErrorClass(errors.password)}
                    />
                    {errors.password && <ErrorText>{errors.password}</ErrorText>}
                </div>


                <button type="submit" className="link-cursor form-btn btn-primary">Sign In</button>
                {errors.invalidCredentials && <ErrorText className="text-center">Invalid username / password</ErrorText>}
            </form>

            <hr />

            <div className="sm:px-20 p-4 gap-2">
                <h3 className="text-lg font-medium text-center">Don't have an account?</h3>
                <Link href="/signup" className="link-cursor btn-link btn-secondary">Sign Up</Link>
            </div>
        </div>
    );
}