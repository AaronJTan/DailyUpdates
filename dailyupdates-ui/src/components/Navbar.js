import { appConfig } from "@/config/appConfig";

export default function Navbar() {
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
        </>
    );
}