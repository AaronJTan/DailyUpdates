import { newsService } from "@/services/NewsService";
import { StringUtils } from "@/utils/StringUtils";

function NewsAPIArticles({ articles }) {
  return (
    <div className="text-sm">
      {articles?.articles.map((article, index) =>
        <div key={index} className="hover:bg-slate-100 py-1 border-t-2">
          <a href={article.url} target="_blank">
            <h2 className="font-bold text-base">{article.title}</h2>
            <div className="italic">By: {article.author}</div>
            <div className="text-[#445a75]">{new Date(article.publishedAt).toLocaleDateString('en-CA', {
              year: 'numeric',
              month: 'long',
              day: 'numeric'
            })}
            </div>
          </a>
        </div>
      )}
    </div>
  );
}

export default async function CategoryPage({ params }) {
  let error = false;
  let articles;
  const category = params.category;
  try {
    articles = await newsService.getTopHeadlinesByCategory(category);
  } catch (err) {
    error = true;
  }

  return (
    <div className="container">
      <h2 className="font-bold">{StringUtils.capitalizeFirstLetter(category)}</h2>
      <div className="text-sm">
        <NewsAPIArticles articles={articles} />
      </div>
    </div>
  )
}
