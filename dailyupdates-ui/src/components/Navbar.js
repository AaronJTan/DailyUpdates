"use client"

import { usePathname } from 'next/navigation'
import { appConfig } from "@/config/appConfig";
import { navigationConfig } from "@/config/navigationConfig";
import Link from "next/link";

export default function Navbar() {
    const pathname = usePathname();

    const todaysDate = new Date().toLocaleString("en-CA", {
        weekday: "long",
        month: "short",
        day: "numeric"
    });

    return (
        <>
            <div className="sm:hidden py-1 container bg-[#272397]">
                <div className="sm:block text-md text-[#bbc6ff]">{todaysDate}</div>
            </div>

            <div className="bg-[#3732b3] border-b-2">
                <div className="container py-3 grid grid-cols-4 items-center">
                    <div className="hidden sm:block text-md text-[#bbc6ff]">{todaysDate}</div>

                    <h1
                        className={`
                            sm:text-center tracking-tight 
                            font-bold col-span-full sm:col-start-2 sm:col-span-2 text-white
                        `}
                    >
                        {appConfig.appName}
                    </h1>
                </div>
            </div>

            <nav className="p-2 mb-2 border-b-4 border-b-skate-200 bg-white">
                <ul className="container flex gap-4 text-[#445a75]">
                    {navigationConfig.map((item, index) => (
                        <li key={index} className="font-bold">
                            <Link href={item.path}
                                className={`${pathname === item.path ? 'border-b-4 border-b-[#3732b3]' : ''}`}
                            >
                                {item.name}
                            </Link>
                        </li>
                    ))}
                </ul>
            </nav>
        </>
    );
}