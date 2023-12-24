"use client"

import Link from "next/link";

export default function SignInPage() {
    return (
        <div className="mx-auto max-w-[600px] my-4 bg-white">

            <form className="flex flex-col sm:px-20 p-4 gap-2">
                <div>
                    <label for="username">Email or username</label>
                    <input type="text" id="username" name="username"
                    />
                </div>

                <div>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password"
                    />
                </div>


                <button type="submit" className="link-cursor btn-primary">Sign In</button>
            </form>

            <hr />

            <div className="sm:px-20 p-4 gap-2">
                <h3 className="text-lg font-medium text-center">Don't have an account?</h3>
                <Link href="/signup" className="link-cursor btn-link btn-secondary">Sign Up</Link>
            </div>
        </div>
    );
}