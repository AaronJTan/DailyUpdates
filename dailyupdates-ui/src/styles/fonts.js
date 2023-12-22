import { Merriweather } from "next/font/google";
import { Roboto } from "next/font/google";
import { IM_Fell_French_Canon_SC } from "next/font/google";
import { Work_Sans } from "next/font/google";

const merriweather = Merriweather({
    weight: "400",
    subsets: ["latin"],
});

const roboto = Roboto({
    weight: "400",
    subsets: ["latin"],
});

const fellFrench = IM_Fell_French_Canon_SC({
    weight: "400",
    subsets: ["latin"],
    display: "swap",
});

const workSans = Work_Sans({
    subsets: ["latin"],
    display: "swap",
});

export { merriweather, roboto, fellFrench , workSans};