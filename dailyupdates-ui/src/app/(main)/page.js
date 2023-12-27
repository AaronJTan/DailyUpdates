"use client"

import { useAuth } from "@/hooks/authContext";
import Link from "next/link";

export default function Home() {

  const { currentUser } = useAuth();

  if (currentUser) {
    return (
      <div className="container">
        <p>Welcome, {currentUser.username}</p>

        <p>The custom feed feature is currently under development.</p>
        <p>In the meantime, feel free to explore our news articles.</p>
      </div>
    );
  }


  return (
    <div className="container mt-4 text-center">
      <h2>Welcome to The Daily Updates - Your Personalized News Hub.</h2>
      <p>
        📰 Tailor your news experience with custom feeds from sources that matter to you.
      </p>
      <p>
        💾 Found an article worth revisitng? Save it for later!
      </p>

      <div className="flex flex-col mt-3 items-center gap-3">
        <Link href="/signup" className="link-cursor btn-primary px-3 w-full sm:w-96 block">Sign Up</Link>
        <Link href="/signin" className="link-cursor btn-secondary px-3 w-full sm:w-96 block">Sign In</Link>
      </div>

      <div className="text-left mt-8">
        <p>The custom feed feature is currently under development.</p>
        <p>In the meantime, feel free to explore our news articles.</p>
      </div>
    </div>
  )
}
