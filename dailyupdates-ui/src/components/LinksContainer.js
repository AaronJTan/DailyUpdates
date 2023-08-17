import FetchError from "./ErrorMessages/FetchError";

export default function LinkContainer({error, siteName, children}) {
    return (
        <div className="border-t border-[#555] my-1">
            <h3>{siteName}</h3>

            {error && <FetchError />}

            {!error && children}
        </div>
    );
}