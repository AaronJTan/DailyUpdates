import { appConfig } from "@/config/appConfig";
import { footnoteLinks } from "@/config/footnoteLinksConfig";

export default function Footer() {
    return (
        <div className="text-center text-gray-700 text-xs mt-2 border-t-2">
            <div className="p-4">
                <div className="flex flex-wrap justify-center gap-x-2 mb-2">
                    {footnoteLinks.map((data, index) => (
                        <div>{data.name}</div>
                    ))}
                </div>
                
                <div>
                    Copyright &copy;{new Date().getFullYear()} {appConfig.appName}. All Rights Reserved.
                </div>
            </div>
        </div>
    );
}