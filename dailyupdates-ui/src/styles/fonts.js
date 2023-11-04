import { Merriweather } from "next/font/google";
import { Roboto } from "next/font/google";

const merriweather = Merriweather({
    weight: "400",
    subsets: ["latin"],
});

const roboto = Roboto({
    weight: "400",
    subsets: ["latin"],
});

export { merriweather, roboto };