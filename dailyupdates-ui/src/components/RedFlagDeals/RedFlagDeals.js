import getRFDHotDeals from "@/services/RFDService"
import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import "./styles.css"

export default async function RedFlagDeals() {
    const deals = await getRFDHotDeals();

    const Deal = ({deal}) => {
        return (
            <div className="card-body-tr border-r" key={deal.topic_id}>
                <a href={deal.web_path} target="_blank">
                    {deal.offer.dealer_name && <span className="mr-2 border border-slate-300 bg-slate-200 rounded-md">{deal.offer.dealer_name}</span>}
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
                <div className="grid gap-1 grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
                    {deals.data.map((deal) => 
                        <Deal deal={deal} />
                    )}
                </div>
            </CardBody>
        </Card>
    );
}