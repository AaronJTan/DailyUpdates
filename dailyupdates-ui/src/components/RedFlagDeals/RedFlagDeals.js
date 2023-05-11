import getRFDHotDeals from "@/services/RFDService"
import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import "./styles.css"

export default async function RedFlagDeals() {
    const deals = await getRFDHotDeals();

    const Deal = ({deal}) => {
        return (
            <div className="link-item border-r" key={deal.topic_id}>
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
                <div className="deals-grid">
                    {deals.data.map((deal) => 
                        <Deal deal={deal} />
                    )}
                </div>
            </CardBody>
        </Card>
    );
}