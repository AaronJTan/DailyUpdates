import { appConfig } from "@/config/appConfig"
import { fellFrench } from "@/styles/fonts"
import Link from "next/link"


export default function AuthLayout({ children }) {
    return (
        <>
            <header>
                <div className="border-b-2">
                    <div className='container text-center items-center p-3'>
                        <Link href="/" className="col-span-6" prefetch={false}>
                            <h1 className={`${fellFrench.className} tracking-tight text-center text-4xl`}>{appConfig.appName}</h1>
                        </Link>
                    </div>
                </div>
            </header>

            <main className="flex-1 bg-[rgba(244,244,245,1)]">{children}</main>
        </>
    )
}