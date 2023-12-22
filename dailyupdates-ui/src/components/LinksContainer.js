import FetchError from "./ErrorMessages/FetchError";

export default function LinkContainer({error, siteName, children}) {
    return (
        <div className="text-sm border-t border-[#555] my-1">
            <h3 className="font-bold">{siteName}</h3>

            {error && <FetchError />}

            {!error && children}
        </div>
    );
}