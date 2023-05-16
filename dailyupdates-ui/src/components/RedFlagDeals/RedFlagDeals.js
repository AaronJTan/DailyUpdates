import getRFDHotDeals from "@/services/RFDService"
import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import "./styles.css"
import FetchError from "../ErrorMessages/FetchError";

export default async function RedFlagDeals() {
    let error = false;
    let deals;
    
    try {
        deals = await getRFDHotDeals();
    } catch (err) {
        error = true;
    }

    const Deal = ({deal, key}) => {
        return (
            <div className="link-item border-r" key={key}>
                <a href={deal.web_path} target="_blank">
                    {deal.offer.dealer_name && <span className="retailer-tag">{deal.offer.dealer_name}</span>}
                    {deal.title}
                </a>
            </div>
        );
    }

    return (
        <Card className="rfd-card"> 
            <CardTitle className="font-semibold">
                RedFlagDeals Hot Deals
            </CardTitle>
            <CardBody>
                {
                    error ? <FetchError /> :
                    
                    <div className="deals-grid">
                        {deals.data.map((deal, index) => 
                            <Deal deal={deal} key={index} />
                        )}
                    </div>
                }
            </CardBody>
        </Card>
    );
}