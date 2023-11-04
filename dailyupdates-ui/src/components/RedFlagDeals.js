import { dealsService } from "@/services/DealsService";
import ExternalLink from "./ExternalLink";
import LinkContainer from "./LinksContainer";

export default async function RedFlagDeals() {
    let error = false;
    let deals;

    try {
        deals = await dealsService.getRFDHotDeals();
    } catch (err) {
        error = true;
    }

    return (
        <LinkContainer error={error} siteName="RedFlagDeals Hot Deals">
            {deals && <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
                {deals?.data.map((deal, index) =>
                    <ExternalLink key={index} href={deal.url}>
                        {deal.offer.dealer_name &&
                            <span className="mr-2 border border-slate-300 bg-slate-200 rounded-md inline-block">
                                {deal.offer.dealer_name}
                            </span>
                        }
                        {deal.title}
                    </ExternalLink>
                )}
            </div>}
        </LinkContainer>
    );
}