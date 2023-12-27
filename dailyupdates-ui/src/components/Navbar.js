"use client"

import { usePathname } from 'next/navigation'
import { appConfig } from "@/config/appConfig";
import { navigationConfig } from "@/config/navigationConfig";
import Link from "next/link";
import { fellFrench } from "@/styles/fonts";
import { GiHamburgerMenu } from "react-icons/gi";
import { IoSearch } from "react-icons/io5";
import { FaUser } from "react-icons/fa";
import { IoMdClose } from "react-icons/io";
import { useState } from 'react';
import { useAuth } from '@/hooks/authContext';

function HambugerMenu({ navbarOpen, setNavBarOpen }) {
    const { currentUser, logoutUser } = useAuth();

    return (
        <nav className={`${navbarOpen ? "block" : "hidden"} w-screen h-screen fixed top-0 left-0 bg-[#161617] text-[#e8e8ed] p-4 text-xl`}>
            <div className='bg-red-200'>
                <IoMdClose className="w-7 h-7 float-right" onClick={() => setNavBarOpen(false)} />
            </div>
            <ul className="container flex flex-col gap-4">
                {navigationConfig.map((item, index) => (
                    <li key={index} className="font-bold">
                        <Link href={item.path} onClick={() => setNavBarOpen(!navbarOpen)}>
                            {item.name}
                        </Link>
                    </li>
                ))}

                {currentUser &&
                    <li className="font-bold border-2 p-2">
                        <div>Welcome, {currentUser.username}</div>
                        <button className="link-cursor btn-primary px-3" onClick={logoutUser}>Log Out</button>
                    </li>
                }
            </ul>
        </nav>
    )
}

export default function Navbar() {
    const { currentUser, logoutUser } = useAuth();
    const pathname = usePathname();
    const [navbarOpen, setNavBarOpen] = useState(false);

    const todaysDate = new Date().toLocaleString("en-CA", {
        weekday: "long",
        month: "long",
        day: "numeric",
        year: "numeric"
    });

    return (
        <>
            {/* mobile */}
            <div className="lg:hidden">
                <div className="border-b-2">
                    <div className='container p-3 flex items-center justify-between'>
                        <GiHamburgerMenu className="w-6 h-6" onClick={() => setNavBarOpen(!navbarOpen)} />
                        <Link href="/" prefetch={false}>
                            <h1 className={`${fellFrench.className} tracking-tight`}>{appConfig.appName}</h1>
                        </Link>
                        <IoSearch className="w-6 h-6" />
                    </div>
                </div>
                <div className="border-b-2">
                    <div className="container">
                        <div className="py-1 text-xs">{todaysDate}</div>
                    </div>
                </div>
                <HambugerMenu navbarOpen={navbarOpen} setNavBarOpen={setNavBarOpen} />
            </div>



            <div className="hidden lg:block">
                <div className="border-b-2">
                    {!currentUser &&
                        <div className="flex justify-end pt-3 px-3 gap-3">
                            <Link href="/signup" className="link-cursor btn-primary px-3">Sign Up</Link>
                            <Link href="/signin" className="link-cursor btn-secondary px-3">Sign In</Link>
                        </div>
                    }

                    {currentUser &&
                        <div className="flex justify-end pt-3 px-3 gap-3">
                            <div>Welcome, {currentUser.username}</div>
                            <button className="link-cursor btn-primary px-3" onClick={logoutUser}>Log Out</button>
                        </div>
                    }

                    <div className='container py-10 grid grid-cols-12 items-center justify-between'>
                        <div className="text-sm col-span-3">{todaysDate}</div>
                        <Link href="/" className="col-span-6" prefetch={false}>
                            <h1 className={`${fellFrench.className} tracking-tight text-center lg:text-4xl xl:text-6xl`}>{appConfig.appName}</h1>
                        </Link>
                        <div className="col-span-3 gap-x-2 justify-end flex">
                            <IoSearch className="w-6 h-6" />
                        </div>
                    </div>
                </div>
                <nav className="p-2 mb-2 border-b-4 border-b-skate-200 bg-white">
                    <ul className="container flex gap-4">
                        {navigationConfig.map((item, index) => (
                            <li key={index} className="font-bold">
                                <Link href={item.path} prefetch={false}
                                    className={`${pathname === item.path ? 'border-b-4 border-b-[#3732b3]' : ''}`}
                                >
                                    {item.name}
                                </Link>
                            </li>
                        ))}
                    </ul>
                </nav>
            </div>

        </>
    );
}