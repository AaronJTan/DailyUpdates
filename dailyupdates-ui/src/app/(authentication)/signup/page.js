"use client"

import ErrorText from "@/components/ErrorText";
import { useFormFields } from "@/hooks/useFormFields";
import AuthService from "@/services/AuthService";
import { StringUtils } from "@/utils/StringUtils";
import { handleInputErrorClass } from "@/utils/cssUtils";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function () {
    const router = useRouter();
    const [formData, handleChange] = useFormFields({
        "first-name": "",
        "last-name": "",
        username: "",
        email: "",
        password: "",
        "confirm-password": "",
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
            const res = await AuthService.signup(formData);
            router.push("/signin");
        } catch (error) {
            if (error.httpStatus == 409) {
                // setErrors({ invalidCredentials: true })
            }
        }
    }

    const validateForm = (formData) => {
        const errors = {};

        Object.keys(formData).forEach(key => {
            if (!formData[key]) {
                let inputField = StringUtils.capitalizeFirstLetter(key);
                inputField = StringUtils.removeDashes(inputField)
                errors[key] = `${inputField} is required`;
            }
        });

        return errors;
    }

    return (
        <div className="mx-auto max-w-[600px] my-4 bg-white">

            <form className="flex flex-col sm:px-20 p-4 gap-2"
                onSubmit={onSubmit}
            >
                <div>
                    <label htmlFor="first-name">First Name</label>
                    <input type="text" id="first-name" name="first-name"
                        value={formData["first-name"]} onChange={handleChange}
                        className={handleInputErrorClass(errors["first-name"])}
                    />
                    {errors["first-name"] && <ErrorText>{errors["first-name"]}</ErrorText>}
                </div>

                <div>
                    <label htmlFor="last-name">Last Name</label>
                    <input type="text" id="last-name" name="last-name"
                        value={formData["last-name"]} onChange={handleChange}
                        className={handleInputErrorClass(errors["last-name"])}
                    />
                    {errors["last-name"] && <ErrorText>{errors["last-name"]}</ErrorText>}
                </div>

                <div>
                    <label htmlFor="username">Username</label>
                    <input type="text" id="username" name="username"
                        value={formData.username} onChange={handleChange}
                        className={handleInputErrorClass(errors.username)}
                    />
                    {errors.username && <ErrorText>{errors.username}</ErrorText>}
                </div>

                <div>
                    <label htmlFor="email">Email</label>
                    <input type="text" id="email" name="email"
                        value={formData.email} onChange={handleChange}
                        className={handleInputErrorClass(errors.email)}
                    />
                    {errors.email && <ErrorText>{errors.email}</ErrorText>}
                </div>

                <div>
                    <label htmlFor="password">Password</label>
                    <input type="password" id="password" name="password"
                        value={formData.password} onChange={handleChange}
                        className={handleInputErrorClass(errors.password)}
                    />
                    {errors.password && <ErrorText>{errors.password}</ErrorText>}
                </div>

                <div>
                    <label htmlFor="confirm-password">Confirm Password</label>
                    <input type="password" id="confirm-password" name="confirm-password"
                        value={formData["confirm-password"]} onChange={handleChange}
                        className={handleInputErrorClass(errors["confirm-password"])}
                    />
                    {errors["confirm-password"] && <ErrorText>{errors["confirm-password"]}</ErrorText>}
                </div>

                <button type="submit" className="link-cursor btn-primary">Sign Up</button>
            </form>

            <hr />

            <div className="sm:px-20 p-4 gap-2">
                <h3 className="text-lg font-medium text-center">Already have an account?</h3>
                <Link href="/signin" className="link-cursor btn-link btn-secondary">Sign In</Link>
            </div>
        </div>
    );
}