import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import {getMarkhamNews} from "../../services/NewsService.js"

export default async function MarkhamNews() {
    const headlines = await getMarkhamNews();

    const Headline = ({headline}) => {
        return (
            <div className="link-item">
                <a href={headline.link} target="_blank">
                    {headline.headline}
                </a>
            </div>
        );
    }

    return (
        <Card> 
            <CardTitle className="font-semibold">
                Markham News
            </CardTitle>
            <CardBody>
                {headlines.map((headline) => 
                    <Headline headline={headline} />
                )}
            </CardBody>
        </Card>
    );
}