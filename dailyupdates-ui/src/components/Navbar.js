import { merriweather } from "@/styles/fonts";

export default function Navbar() {
    const todaysDate = new Date().toLocaleString("en-CA", {
        weekday: "long",
        month: "short",
        day: "numeric"
    });

    return (
        <div className="bg-white border-b-2">
            <div className="container py-3 grid grid-cols-4 items-center">
                <div className="hidden sm:block text-sm text-gray-700">{todaysDate}</div>

                <h1
                    className={
                        `${merriweather.className} text-center tracking-tight 
                         font-bold col-start-2 col-span-2`
                    }
                >
                    DAILY UPDATES
                </h1>
            </div>
        </div>
    );
}