import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import {getTheHackerNewsArticles} from "../../services/NewsService.js"
import "./styles.css"

export default async function TheHackerNews() {
    const headlines = await getTheHackerNewsArticles();

    const Headline = ({headline}) => {
        return (
            <tr className="card-body-tr">
                <td>
                    <a href={headline.url} target="_blank">
                        {headline.headline}
                    </a>
                </td>
            </tr>
        );
    }

    return (
        <Card> 
            <CardTitle className="font-semibold">
                The Hacker News
            </CardTitle>
            <CardBody>
                <table className="table-auto">
                    <tbody>
                        {headlines.data.map((headline) => 
                            <Headline headline={headline} />
                        )}
                    </tbody>
                </table>
            </CardBody>
        </Card>
    );
}