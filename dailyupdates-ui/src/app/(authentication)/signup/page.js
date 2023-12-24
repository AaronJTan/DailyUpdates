"use client"

import Link from "next/link";

export default function () {
    return (
        <div className="mx-auto max-w-[600px] my-4 bg-white">

            <form className="flex flex-col sm:px-20 p-4 gap-2">
                <div>
                    <label for="first-name">First Name</label>
                    <input type="text" id="first-name" name="first-name"
                    />
                </div>

                <div>
                    <label for="last-name">Last Name</label>
                    <input type="text" id="last-name" name="last-name"
                    />
                </div>

                <div>
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username"
                    />
                </div>

                <div>
                    <label for="email">Email</label>
                    <input type="text" id="email" name="email"
                    />
                </div>

                <div>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password"
                    />
                </div>

                <div>
                    <label for="confirm-password">Confirm Password</label>
                    <input type="password" id="confirm-password" name="confirm-password"
                    />
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