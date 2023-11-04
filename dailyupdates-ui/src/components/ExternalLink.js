
export default function ExternalLink({ children, href }) {
    return (
        <div className="border p-0.5 hover:bg-slate-100">
            <a href={href} target="_blank">
                {children}
            </a>
        </div>
    );
}