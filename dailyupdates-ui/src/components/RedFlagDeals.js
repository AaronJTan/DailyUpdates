import getRFDHotDeals from "@/services/RFDService"
import FetchError from "./ErrorMessages/FetchError";
import { merriweather } from "@/styles/fonts";

export default async function RedFlagDeals() {
    let error = false;
    let deals;

    try {
        deals = await getRFDHotDeals();
    } catch (err) {
        error = true;
    }

    return (
        <div className="col-span-full border-t border-[#555]">
            <h3>RedFlagDeals Hot Deals</h3>

            {error && <FetchError />}

            {
                !error &&
                (<div className="grid gap-1 grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
                    {deals.data.map((deal, index) =>
                        <div className={`border p-0.5 hover:bg-slate-100 ${merriweather.className}`} key={index}>
                            <a href={deal.web_path} target="_blank">
                                {deal.offer.dealer_name &&
                                    <span className="mr-2 border border-slate-300 bg-slate-200 rounded-md inline-block">
                                        {deal.offer.dealer_name}
                                    </span>
                                }
                                {deal.title}
                            </a>
                        </div>
                    )}
                </div>)
            }
        </div>
    )
}