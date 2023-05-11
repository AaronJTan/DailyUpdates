import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import {getCP24LatestNews} from "../../services/NewsService.js"
import { cp24Category } from "./categories";

export default async function CP24News({category}) {
    category = category || cp24Category.LATESTNEWS;
    const headlines = await getCP24LatestNews(category.path);

    const Headline = ({headline}) => {
        return (
            <div className="link-item">
                <a href={headline.url} target="_blank">
                    {headline.headline}
                </a>
            </div>
        );
    }

    return (
        <Card> 
            <CardTitle className="font-semibold">
                CP24 {category.title}
            </CardTitle>
            <CardBody>
                {headlines.data.map((headline) => 
                    <Headline headline={headline} />
                )}
            </CardBody>
        </Card>
    );
}