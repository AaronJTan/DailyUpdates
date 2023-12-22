"use client"

import { usePathname } from 'next/navigation'
import { appConfig } from "@/config/appConfig";
import { navigationConfig } from "@/config/navigationConfig";
import Link from "next/link";
import { fellFrench } from "@/styles/fonts";
import { GiHamburgerMenu } from "react-icons/gi";
import { IoSearch } from "react-icons/io5";
import { FaUser } from "react-icons/fa";

export default function Navbar() {
    const pathname = usePathname();

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
                        <GiHamburgerMenu className="w-6 h-6" />
                        <h1 className={`${fellFrench.className} tracking-tight`}>{appConfig.appName}</h1>
                        <IoSearch className="w-6 h-6" />
                    </div>
                </div>
                <div className="border-b-2">
                    <div className="container">
                        <div className="py-1 text-xs">{todaysDate}</div>
                    </div>
                </div>
            </div>


            <div className="hidden lg:block">
                <div className="border-b-2">
                    <div className='container py-10 grid grid-cols-12 items-center justify-between'>
                        <div className="text-sm col-span-3">{todaysDate}</div>
                        <h1 className={`${fellFrench.className} col-span-6 tracking-tight text-center lg:text-4xl xl:text-6xl`}>{appConfig.appName}</h1>
                        <div className="col-span-3 gap-x-2 justify-end flex">
                            <IoSearch className="w-6 h-6" />
                        </div>
                    </div>
                </div>
                <nav className="p-2 mb-2 border-b-4 border-b-skate-200 bg-white">
                    <ul className="container flex gap-4">
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
            </div>

        </>
    );
}